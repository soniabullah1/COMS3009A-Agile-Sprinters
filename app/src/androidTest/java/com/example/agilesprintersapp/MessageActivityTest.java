package com.example.agilesprintersapp;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageActivityTest {

    @Rule
    public ActivityTestRule<MessageActivity> messageActivityTestRule = new ActivityTestRule<>(MessageActivity.class);
    private MessageActivity messageActivity = null;

    @Before
    public void setUp() throws Exception {
        messageActivity = messageActivityTestRule.getActivity();
        //messageActivity.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    @Test
    public void testLaunch(){
        View view = messageActivity.findViewById(R.id.recycler_view12);
        assertNotNull(view);
    }

    @Test
    public void testLaunchEditText_Send(){
        View view = messageActivity.findViewById(R.id.text_send);
        assertNotNull(view);

    }


    @Test
    public void testMessageSendButton(){
        onView(withId(R.id.btn_send)).perform(click());
        boolean toastMade = messageActivity.toastMade;
        assertEquals(true, toastMade);
    }

    @Test
    public void testCheckVariables(){
        CircleImageView expected1 = messageActivity.profile_image;
        TextView expected2 = messageActivity.username;
        FirebaseUser expected3 = messageActivity.fuser;
        DatabaseReference expected4 = messageActivity.reference;
        ImageButton expected5 = messageActivity.btn_send;

        boolean checker1 = false;
        boolean checker2 = false;
        boolean checker3 = false;
        boolean checker4 = false;
        boolean checker5 = false;

        if(expected1 != null){
             checker1 = true;
        }
        if(expected2 == null){
            checker2 = false;
        }
        if(expected3 == null){
            checker3 = false;
        }
        if(expected4 == null){
            checker4 = false;
        }
        if(expected5 == null){
            checker5 = false;
        }

        assertNotNull(checker1);
        assertNotNull(checker2);
        assertNotNull(checker3);
        assertNotNull(checker4);
        assertNotNull(checker5);

    }

    @After
    public void tearDown() throws Exception {
        messageActivity = null;
    }
}