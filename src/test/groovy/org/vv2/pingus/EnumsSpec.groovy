package org.vv2.pingus

import spock.lang.Specification

class EnumsSpec extends Specification{

    void "Enum DESCRICAO"() {
        when:"The enum list is complete"

        then:"Should contains"
        SearchFields.DESCRICAO.toString() == "DESCRICAO"
    }

    void "Enum RETIRADA"() {
        when:"The enum list is complete"

        then:"Should contains"
        SearchFields.RETIRADA.toString() == "RETIRADA"
    }
}
