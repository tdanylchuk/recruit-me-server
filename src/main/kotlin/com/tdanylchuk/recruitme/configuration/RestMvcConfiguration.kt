package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.repository.entity.AttachmentEntity
import com.tdanylchuk.recruitme.repository.entity.CandidateEntity
import com.tdanylchuk.recruitme.repository.entity.UserEntity
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter

@Configuration
class RestMvcConfiguration : RepositoryRestConfigurerAdapter() {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        config!!.exposeIdsFor(
                CandidateEntity::class.java,
                AttachmentEntity::class.java,
                UserEntity::class.java
        )
    }
}