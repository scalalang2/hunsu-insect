package com.hunsu.backend.models

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
data class Account (var createdAt: OffsetDateTime = OffsetDateTime.now(),
                    var updatedAt: OffsetDateTime = OffsetDateTime.now(),

                    @Column(unique = true)
                 var username: String = "",
                    var password: String = "",

                    @Column(unique = true)
                 var displayName: String = "",

                    @Id
                 @GeneratedValue(strategy = GenerationType.AUTO)
                 var id: Long? = null,
)
