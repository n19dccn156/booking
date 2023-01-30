package com.be.booking.Models.Account;

import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
public class NotificationModel {
    
    @Id
    private ObjectId _id;
    private String userId;
    private String image;
    private String title;
    private String description;
    private String datetime;
}
