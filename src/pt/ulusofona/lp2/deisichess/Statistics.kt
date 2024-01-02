package pt.ulusofona.lp2.deisichess

object StatisticsKt {

    @JvmStatic
    fun getStatsCalculator(type: StatType): Function1<GameManager,List<String>> {
        return when (type) {
            StatType.TOP_5_CAPTURAS -> ::top5Capturas
            StatType.TOP_5_PONTOS -> ::top5Pontos
            StatType.PECAS_MAIS_5_CAPTURAS -> ::pecasMais5Capturas
            StatType.PECAS_MAIS_BARALHADAS -> ::pecasMaisBaralhadas
            StatType.TIPOS_CAPTURADOS -> ::tiposCapturados

        }
    }

    private fun top5Capturas(manager: GameManager): List<String> {
        val pecasCapturadas = manager.getTabuleiro().getPecas()
                .filter { !it.value.isCapturado } // Filtrar peças não capturadas
                .map { it.value } // Mapear para as peças

        val capturasPorPeca = pecasCapturadas.groupBy(
                keySelector = { it.nome }, // Agrupar por nome da peça
                valueTransform = { it.capturas } // Transformar em uma lista de capturas
        ).mapValues { it.value.sum() } // Somar as capturas por nome de peça

        val sortedCaptures = capturasPorPeca.toList().sortedByDescending { it.second } // Ordenar as capturas

        val topPecas = sortedCaptures.take(5) // Pegar as 5 primeiras

        return topPecas.map { (pecaNome, capturas) ->
            val peca = manager.getTabuleiro().getPecas().values.find { it.nome == pecaNome }
            val equipaString = if (peca?.equipa == 10) "(PRETA)" else "(BRANCA)"
            "$pecaNome $equipaString fez $capturas capturas"
        }
    }

    private fun top5Pontos(manager: GameManager): List<String> {
        val dimensao = manager.getTabuleiro().getDimensao()

        val pecasPontos = manager.getTabuleiro().getPecas()
                .filter { !it.value.isCapturado }
                .map { it.value }
                .groupBy(
                        keySelector = { it.nome },
                        valueTransform = { it.getPontuacaoCapturas() }
                )
                .mapValues { it.value.sum() }
                .toList()
                .filter { it.second > 0 }
                .sortedByDescending { it.second }
                .take(5)

        return pecasPontos.map { (pecaNome, pontos) ->
            val peca = manager.getTabuleiro().getPecas().values.find { it.nome == pecaNome }
            val equipaString = if (peca?.equipa == 10) "(PRETA)" else "(BRANCA)"
            "$pecaNome $equipaString tem $pontos pontos"
        }
    }
    private fun pecasMais5Capturas(manager: GameManager): List<String> {
        return manager.getTabuleiro().getPecas()
                .filter { !it.value.isCapturado }
                .filter { it.value.getCapturas() > 5 }
                .map { it.value }
                .map { piece ->
                    val pecaNome = piece.getNome()
                    val pecaEquipa = if (piece.getEquipa() == 10) "PRETA" else "BRANCA"
                    val capturas = piece.getCapturas()
                    "$pecaEquipa:$pecaNome:$capturas"
                }
    }

    private fun pecasMaisBaralhadas(manager: GameManager): List<String> {
        val pecasInvalidas = manager.getTabuleiro().getPecas()
                .filter { it.value.getJogadasInvalidas() > 0 }
                .map { it.value }

        val maiorNumeroInvalidas = pecasInvalidas.maxByOrNull { it.getJogadasInvalidas() }?.getJogadasInvalidas() ?: 0

        val maisBaralhadas = pecasInvalidas.filter { it.getJogadasInvalidas() == maiorNumeroInvalidas }

        return maisBaralhadas.map { peca ->
            "${peca.getEquipa()}:${peca.getNome()}:${peca.getJogadasInvalidas()}:${peca.getJogadasValidas()}"
        }
    }

    private fun tiposCapturados(manager: GameManager): List<String> {
        val tipos = mapOf(
                0 to "Rei",
                1 to "Rainha",
                2 to "Pónei mágico",
                3 to "Padre da vila",
                4 to "Torre Horizontal",
                5 to "Torre Vertical",
                6 to "Homer Simpson",
                7 to "Joker"
        )

        val tiposCapturados = manager.getTabuleiro().getPecas()
                .filter { it.value.isCapturado() }
                .mapNotNull { tipos[it.value.getTipo()] }

        return tiposCapturados.distinct()
    }
}