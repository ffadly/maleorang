package com.ecwid.maleorang.method.v3_0.lists.members

import com.ecwid.maleorang.MailchimpClient
import com.ecwid.maleorang.MailchimpException
import com.ecwid.maleorang.MailchimpObject
import org.testng.Assert.assertEquals
import org.testng.Assert.fail
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Parameters
import org.testng.annotations.Test
import java.util.*

class MembersTest
@Parameters("mailchimp.test.apikey", "mailchimp.test.listid")
constructor(private val apiKey: String, private val listId: String) {

    @Test
    fun test_POST_PATCH() {
        MailchimpClient(apiKey).use { client ->
            val email = "ferry.fadly.games44@gmail.com"

            // Delete
            try {
                client.execute(DeleteMemberMethod(listId, email))
            }catch(e : Exception ){
                //nothing
            }

            // Creating the user
            client.execute(EditMemberMethod.Create(listId, email).apply {
                status = "subscribed"
                merge_fields = MailchimpObject().apply {
                    mapping["FNAME"] = "Vasya"
                    mapping["LNAME"] = "Pupkin"
                }
                timestamp_signup = Date(0)
            }).apply {
                assertEquals(email_address, email)
                assertEquals(status, "subscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin")
                assertEquals(timestamp_signup, Date(0))
            }

            // Updating the user using PATCH method
            client.execute(EditMemberMethod.Update(listId, email).apply {
                status = "unsubscribed"
                merge_fields = MailchimpObject().apply {
                    mapping["FNAME"] = "Vasya1"
                    mapping["LNAME"] = "Pupkin1"
                }
                timestamp_signup = Date(10000)
            }).apply {
                assertEquals(email_address, email)
                assertEquals(status, "unsubscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya1")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin1")
                assertEquals(timestamp_signup, Date(10000))
            }

            // Get
            client.execute(GetMemberMethod(listId, email)).apply {
                assertEquals(email_address, email)
                assertEquals(status, "unsubscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya1")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin1")
                assertEquals(timestamp_signup, Date(10000))
            }

            // Delete
            client.execute(DeleteMemberMethod(listId, email))
        }
    }

    @Test
    fun test_PUT() {
        MailchimpClient(apiKey).use { client ->
            val email = "ferry.fadly.games55@gmail.com"

            // Delete
            try {
                client.execute(DeleteMemberMethod(listId, email))
            }catch(e : Exception){
                //nothing
            }

            // Create
            client.execute(EditMemberMethod.CreateOrUpdate(listId, email).apply {
                status = "subscribed"
                merge_fields = MailchimpObject().apply {
                    mapping["FNAME"] = "Vasya"
                    mapping["LNAME"] = "Pupkin"
                }
                timestamp_signup = Date(1000000)
            }).apply {
                assertEquals(email_address, email)
                assertEquals(status, "subscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin")
                assertEquals(timestamp_signup, Date(1000000))
            }

            // Update
            client.execute(EditMemberMethod.CreateOrUpdate(listId, email).apply {
                status = "unsubscribed"
                merge_fields = MailchimpObject().apply {
                    mapping["FNAME"] = "Vasya1"
                    mapping["LNAME"] = "Pupkin1"
                }
                timestamp_signup = Date(1005000)
            }).apply {
                assertEquals(email_address, email)
                assertEquals(status, "unsubscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya1")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin1")
                assertEquals(timestamp_signup, Date(1005000))
            }

            // Get
            client.execute(GetMemberMethod(listId, email)).apply {
                assertEquals(email_address, email)
                assertEquals(status, "unsubscribed")
                assertEquals(merge_fields!!.mapping["FNAME"], "Vasya1")
                assertEquals(merge_fields!!.mapping["LNAME"], "Pupkin1")
                assertEquals(timestamp_signup, Date(1005000))
            }

            // Delete
            client.execute(DeleteMemberMethod(listId, email))
        }
    }
}
