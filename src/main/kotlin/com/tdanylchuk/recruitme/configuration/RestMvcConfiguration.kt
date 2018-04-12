package com.tdanylchuk.recruitme.configuration

import com.tdanylchuk.recruitme.model.Candidate
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter

@Configuration
class RestMvcConfiguration : RepositoryRestConfigurerAdapter() {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        config!!.exposeIdsFor(Candidate::class.java)
    }
}