package org.exoplatform.bch.activity.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.exoplatform.bch.activity.Activity;
import org.exoplatform.bch.activity.client.retrofit.HttpRestClientException;
import retrofit.client.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by bdechateauvieux on 5/8/15.
 */
public class ActivityTableSD extends BaseStepDefinitions {
    private int httpErrorStatus;

    @When("^I publish an activity with title \"(.*)\"$")
    public void iPublishActivity(String title) throws Throwable {
        //TODO authenticate
        Response response;
        try {
            response = getActivityClient().post(new Activity(title));
            this.httpErrorStatus = response.getStatus();
        } catch (HttpRestClientException e) {
            this.httpErrorStatus = e.getStatus();
        }
    }

    @Then("^the HTTP status code of the response is (\\d+)$")
    public void theHttpStatusCodeOfTheResponseIs(int statusCode) throws Throwable {
        assertThat(this.httpErrorStatus, is(statusCode));
    }
}
