package com.tdanylchuk.recruitme.service

import com.tdanylchuk.recruitme.exception.ClientException
import com.tdanylchuk.recruitme.exception.ServerException
import com.tdanylchuk.recruitme.model.CompensationRequest
import com.tdanylchuk.recruitme.repository.CompensationCategoryRepository
import com.tdanylchuk.recruitme.repository.CompensationRepository
import com.tdanylchuk.recruitme.repository.EmployeeRepository
import com.tdanylchuk.recruitme.repository.entity.compensation.CompensationEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

const val DEFAULT_GRADE = "default"

@Service
class CompensationService(private val compensationCategoryRepository: CompensationCategoryRepository,
                          private val compensationRepository: CompensationRepository,
                          private val employeeRepository: EmployeeRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun compensate(compensationRequest: CompensationRequest) {

        val limit = getLimit(compensationRequest)
        val employeeCompensations = getEmployeeCompensations(compensationRequest.employeeId)

        if (employeeCompensations + compensationRequest.amount > limit) {
            throw ClientException("Compensation limit[$limit] has been exceeded. " +
                    "Employee compensation - [$employeeCompensations] " +
                    "+ requested amount [${compensationRequest.amount}]")
        }

        val compensationEntity = compensationRepository.save(convertToEntity(compensationRequest))
        log.info("New compensation has been saved - [$compensationEntity]")
    }

    private fun getEmployeeCompensations(employeeId: Long): Double {
        val employeeCompensations = compensationRepository.findByEmployeeId(employeeId)
        return employeeCompensations.sumByDouble { compensation -> compensation.amount }
    }

    private fun getLimit(compensationRequest: CompensationRequest): Double {
        val category = compensationCategoryRepository.findByCategory(compensationRequest.category)
        val employee = employeeRepository.getOne(compensationRequest.employeeId)
                ?: throw ClientException("Employee[${compensationRequest.employeeId} does not found.]")
        val limit = category.gradationLimits.find { gradation -> gradation.grade == employee.grade }
                ?: category.gradationLimits.find { gradation -> gradation.grade == DEFAULT_GRADE }
                ?: throw ServerException("Category[${category.id}] does not have [${employee.grade}] grade nor default grade.")
        return limit.limit
    }

    private fun convertToEntity(compensationRequest: CompensationRequest): CompensationEntity {
        return CompensationEntity(category = compensationRequest.category,
                amount = compensationRequest.amount,
                description = compensationRequest.description,
                employeeId = compensationRequest.employeeId)
    }
}