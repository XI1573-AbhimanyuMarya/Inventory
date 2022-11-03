package com.booking.system.repository.enitty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "SparePart")
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPARE_PART_ID")
    private String sparePartId;

    @Column(name = "SPARE_PART_DESCRIPTION")
    private String sparePartDescription;

    @Column(name = "STOCK_COUNT")
    private String stockCount;


    public SparePart(String sparePartDescription, String stockCount) {
        this.sparePartDescription = sparePartDescription;
        this.stockCount = stockCount;
    }
}
