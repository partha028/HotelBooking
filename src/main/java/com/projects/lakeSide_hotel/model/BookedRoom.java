package com.projects.lakeSide_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name= "check_in")
    private LocalDate checkInDate;
    @Column(name= "check_out")
    private LocalDate checkOutDate;
    @Column(name= "guest_fullName")
    private String guestName;
    @Column(name= "guest_email")
    private String guestEmail;
    @Column(name= "adults")
    private int numberOfAdults;
    @Column(name= "children")
    private int numberOfChildren;
    @Column(name= "total_guests")
    private int totalNumOfGuests;
    @Column(name= "confirmation_code")
    private String bookingConfirmationCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "room_id")
    private Room room;

    public void calculateTotalNumberOfGuests(){
        this.totalNumOfGuests = this.numberOfAdults + this.numberOfChildren;
    }

    public void setNumOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumberOfChildren(int numOfChildren) {
        this.numberOfChildren = numOfChildren;
        calculateTotalNumberOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
