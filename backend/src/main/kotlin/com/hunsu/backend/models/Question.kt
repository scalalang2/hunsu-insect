package com.hunsu.backend.models

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
data class Question(var createdAt: OffsetDateTime,
                    var updatedAt: OffsetDateTime,

                    @OneToOne
                    @JoinColumn(name = "user_id")
                    var account: Account,

                    @Column(columnDefinition = "TEXT")
                    var question: String,

                    @Column(columnDefinition = "TEXT")
                    var answer: String,

                    @Id
                    @GeneratedValue(strategy = GenerationType.AUTO)
                    var id: Int? = null,
)