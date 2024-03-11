package com.code.challenge.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table

import com.code.challenge.annotation.NoArg

@Entity
@Table(schema = "test", name = "t_url_info")
@NoArg
class UrlInfo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val id: Long? = null,
        @Column(name = "url") var url: String,
        @Column(name = "hash") var hash: String
) {
}