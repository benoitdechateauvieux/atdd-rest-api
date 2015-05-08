package org.exoplatform.bch.activity.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.exoplatform.bch.activity.Activity;
import retrofit.client.Response;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by bdechateauvieux on 4/23/15.
 */
public class ActivitySD extends BaseStepDefinitions {
    List<Activity> stream = null;
    private long activityId;

    @Given("^I publish an activity$")
    public void iPublishActivity() throws Throwable {
        //TODO authenticate
        Response response = getActivityClient().post(new Activity("title"));
        this.activityId = Long.valueOf(IOUtils.toString(response.getBody().in()));
    }

    @When("^I consult my Activity Stream$")
    public void iConsultActivityStream() throws Throwable {
        //TODO authenticate
        stream = getActivityClient().getStream();
    }

    @Then("^this activity is present in the stream$")
    public void thisActivityIsPresentInTheStream() throws Throwable {
        assertThat(stream, hasItem(hasProperty("id", equalTo(this.activityId))));
    }
}
