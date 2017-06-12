package com.testcase.demo.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.testcase.demo.BuildConfig;
import com.testcase.demo.R;
import com.testcase.demo.RobolectricTestRunnerWithResources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Class LoginActivityUnitTest created on 6/9/17.
 */
@RunWith(RobolectricTestRunnerWithResources.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml", packageName = "com.testcase.demo")
public class LoginActivityUnitTest {

    private EditText emailView;
    private EditText passwordView;
    private Button button;

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }
    @Test
    public void loginSuccess() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }
}
