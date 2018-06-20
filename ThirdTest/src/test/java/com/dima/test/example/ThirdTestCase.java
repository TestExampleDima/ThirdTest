package com.dima.test.example;

import com.jcabi.github.Github;
import com.jcabi.github.Repos;
import com.jcabi.github.RtGithub;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class ThirdTestCase {

    private static final String TOKEN = ""; //Перед тестированием нужно создать свой Personal access tokens
    private static final String REPOSITORY_NAME = "Hello-World";
    private static final String LOGIN_URL = "https://github.com/login?return_to=/"+REPOSITORY_NAME+"/settings";
    private static final String LOGIN = ""; //Перед тестированием нужно ввести свой логин
    private static final String PASS = ""; //Перед тестированием нужно ввести свой пароль


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.github.com/repos/TestExampleDima/" + REPOSITORY_NAME;
    }

    @Test
    public void createRepository() {
        Github github = new RtGithub(TOKEN);
        Repos.RepoCreate repoCreate = new Repos.RepoCreate(REPOSITORY_NAME, false);
        try {
            github.repos().create(repoCreate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeRepositoryByUI() {
        open("https://github.com/login");
        $("#login_field").setValue(LOGIN);
        $("#password").setValue(PASS);
        $("#login > form > div.auth-form-body.mt-3 > input.btn.btn-primary.btn-block").click();
        $("ul.list-style-none.pr-3").find("a.d-flex.flex-items-baseline.flex-items-center.f5.mb-2").click();
        $$("nav.reponav.js-repo-nav.js-sidenav-container-pjax.container a").findBy(text("Settings")).click();
        $("#options_bucket > div.Box.Box--danger > ul > li:nth-child(4) > details > summary").click();
        $("#options_bucket > div.Box.Box--danger > ul > li:nth-child(4) > details > details-dialog > div.Box-body.overflow-auto > form > p > input").setValue(REPOSITORY_NAME);
        $("#options_bucket > div.Box.Box--danger > ul > li:nth-child(4) > details > details-dialog > div.Box-body.overflow-auto > form > button").click();
    }

    @Test
    public void isRepositoryDeleted() {
        given()
                .get()
                .then()
                .assertThat()
                .statusCode(404);
    }
}