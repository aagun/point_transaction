package com.tujuhsembilan.pointtransaction.point.model;

import com.tujuhsembilan.pointtransaction.clients.model.pg.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "points")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "points_gen")
    @SequenceGenerator(name = "points_gen", sequenceName = "points_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Customer customer;

    private Long points;
}
