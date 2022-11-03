package com.booking.system.repository.enitty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "User")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderId;
    private String orderDescription;
    private LocalDateTime createdOn;
    private String createdBy;
    private String userId;
    private String sparePartId;

}
