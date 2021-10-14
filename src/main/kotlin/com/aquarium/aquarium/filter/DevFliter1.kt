package com.aquarium.aquarium.filter

import java.io.IOException
import javax.servlet.*

class DevFliter1 : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("필터1")
        chain?.doFilter(request, response)
    }


}