package com.elemental.firebasechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.elemental.firebasechat.adapter.MessageAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.HashMap
import com.elemental.firebasechat.model.Chat as Chat

class MainActivity : AppCompatActivity() {
    private val chats:MutableList<Chat> = ArrayList()
    private lateinit var messageAdapter:MessageAdapter
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //read message between the two users
        readMessage("linmg","pm")
        btn_send.setOnClickListener {
            val msg=text_send.text.toString()
            if(msg != ""){
                sendMessage("linmg","pm",msg)
            }
            else{
                Toast.makeText(this,"You can't send empty message",Toast.LENGTH_SHORT).show()
            }
            text_send.setText("")
        }

    }
    private fun sendMessage(sender:String,receiver:String,message:String){
        val reference:DatabaseReference = FirebaseDatabase.getInstance().reference
        val hashMap:HashMap<String,Any> = HashMap()
        hashMap["sender"] = sender
        hashMap["receiver"] = receiver
        hashMap["message"] = message

        reference.child("Chats").push().setValue(hashMap)

    }
    private fun readMessage(myid:String,userid:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Chats")

        databaseReference.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(snapshot:DataSnapshot in dataSnapshot.children){

                    val chat=snapshot.getValue<Chat>(Chat::class.java)
                    if(chat!!.receiver.equals(myid) && chat.sender.equals(userid) ||
                        chat.receiver.equals(userid) && chat.sender.equals(myid)){
                        chats.add(chat)
                    }
                    messageAdapter= MessageAdapter(this@MainActivity,chats)
                    val linearLayoutManager=LinearLayoutManager(this@MainActivity)
                    linearLayoutManager.stackFromEnd=true

                    recycler_view.apply {
                        layoutManager=linearLayoutManager
                        setHasFixedSize(true)
                        adapter=messageAdapter
                    }


                }
            }

        })
    }
}
