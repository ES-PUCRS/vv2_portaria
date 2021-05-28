package org.vv2.pingus

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class EntregaCustomService {


    static List<Entrega> searchBy(String field, String[] params)  {
        List<Entrega> list = []
        for(param in params) {
            list += "searchBy${field}"(param)
        }
        return list
    }


//    private static List<Entrega> searchByENTREGA(String id) {
//        def list = []
//        try { list.add(Entrega.findById(Integer.parseInt(id))) } catch (Exception ignored) {}
//        return list
//    }

    private static List<Entrega> searchByDESCRICAO(String descricao) {
        Entrega.all.findAll {
            it.descricao.contains "${descricao}"
        }
    }

//    private static List<Entrega> searchByDATA(String data) {
//        def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")
//        Entrega.all.findAll {
//            boolean result = false
//            if(it.criado) {
//                result = sdf.format(it.criado).toString().contains("${data}")
//            } else {
//                result = sdf.format(it.dateCreated).toString().contains("${data}")
//            }
//            result
//        }
//    }

//    private static List<Entrega> searchByAPTO(String apto) {
//        def list = []
//        try { list = Entrega.findAllByApto(Integer.parseInt(apto)) } catch (Exception ignored) {}
//        return list
//    }

//    private static List<Entrega> searchByOPERADOR(String nome) {
//        Entrega.all.findAll {
//            it.operador.nome.contains "${nome}"
//        }
//    }

    private static List<Entrega> searchByRETIRADA(String data) {
        if (data.isEmpty()) {
            Entrega.findAllWhere(retirado: null)
        } else {
            def sdf = new SimpleDateFormat("dd/MM/yy HH:mm")
            Entrega.all.findAll {
                if (it.retirado)
                    sdf.format(it.retirado).toString().contains("${data}")
            }
        }
    }

//    private static List<Entrega> searchByMORADOR(String nome) {
//        if ( nome.isEmpty() ) {
//            Entrega.findAllWhere(morador: null)
//        } else {
//            Entrega.all.findAll {
//                if (it.morador)
//                    it.morador.nome.toLowerCase().contains "${nome.toLowerCase()}"
//            }
//        }
//    }

}
