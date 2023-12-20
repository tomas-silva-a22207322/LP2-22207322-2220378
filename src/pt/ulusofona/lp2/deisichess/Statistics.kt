package pt.ulusofona.lp2.deisichess

object StatisticsKt {

    @JvmStatic
    fun getStatsCalculator(type: StatType): Function1<GameManager,List<String>> {
        return when (type) {
            StatType.TOP_5_CAPTURAS -> ::top5Capturas
            StatType.TOP_5_PONTOS -> ::top5Pontos
            StatType.PECAS_MAIS_5_CAPTURADAS -> ::pecasMais5Capturas
            StatType.PECAS_MAIS_BARALHADAS -> ::pecasMaisBaralhadas
            StatType.TIPOS_CAPTURADOS -> ::tiposCapturados

        }
    }

    private fun top5Capturas(manager: GameManager): List<String> {

        return emptyList()
    }

    private fun top5Pontos(manager: GameManager): List<String> {

        return emptyList()
    }

    private fun pecasMais5Capturas(manager: GameManager): List<String> {

        return emptyList()
    }

    private fun pecasMaisBaralhadas(manager: GameManager): List<String> {

        return emptyList()
    }

    private fun tiposCapturados(manager: GameManager): List<String> {

        return emptyList()
    }
}