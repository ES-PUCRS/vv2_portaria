package org.vv2.pingus

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/entrega/index")
        "/" ( controller:'Entrega', action:'index' )
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
