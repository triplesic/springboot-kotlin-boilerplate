package org.triplesic.kotlin.boilerplate.controller

import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.util.JRLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.triplesic.kotlin.boilerplate.repository.UserRepository
import java.io.File
import java.io.IOException
import java.sql.SQLException
import javax.naming.NamingException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(val userRepo: UserRepository) {

    @GetMapping
    fun getAllUsers() = userRepo.findAll()

    val resourcePath = "/Users/napasinyamwat/Desktop/"

    @GetMapping("/report")
    fun getReport(request: HttpServletRequest, response: HttpServletResponse) {
        val reportFileName = "sample_report"
        val reportParams = HashMap<String, Any>()
        reportParams.put("BookTitle", "Test book title")
        reportParams.put("BookSubTitle", "Test bokk sub title Yeah!!")

        val jasperReport = getCompiledFile(reportFileName)

        generateReportPDF(response, reportParams, jasperReport)

    }

    private fun getCompiledFile(fileName: String): JasperReport {

        val resourceFile = File(resourcePath + fileName + ".jasper")

        if (!resourceFile.exists()) {
            JasperCompileManager.compileReportToFile(
                    resourcePath + fileName + ".jrxml",
                    resourcePath + fileName + ".jasper")
        }
        return JRLoader.loadObjectFromFile(resourceFile.path) as JasperReport
    }

    @Throws(JRException::class, NamingException::class, SQLException::class, IOException::class)
    private fun generateReportPDF(resp: HttpServletResponse, parameters: Map<String, Any>, jasperReport: JasperReport) {
        var bytes: ByteArray? = null
        bytes = JasperRunManager.runReportToPdf(jasperReport, parameters)
        resp.reset()
        resp.resetBuffer()
        resp.contentType = "application/pdf"
        resp.setContentLength(bytes!!.size)
        val ouputStream = resp.outputStream
        ouputStream.write(bytes, 0, bytes.size)
        ouputStream.flush()
        ouputStream.close()
    }
}