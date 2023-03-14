package com.hunsu.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.time.OffsetDateTime

@Entity
data class Comment (var createdAt: OffsetDateTime,
                    var updatedAt: OffsetDateTime,

                    @OneToOne
                    @JoinColumn(name="user_id")
                    var account: Account,

                    @OneToOne
                    @JoinColumn(name="question_id")
                    var question: Question,

                    @Column(columnDefinition = "TEXT")
                    var comment: String,

                    @Id
                    @GeneratedValue(strategy = GenerationType.AUTO)
                    var id: Int? = null,
)