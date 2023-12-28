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
        val pecasCapturadas = mutableMapOf<String, Pair<Int, Int>>() // Pair<Quantidade de Capturas, Equipa>
        val dimensao = manager.getTabuleiro().getDimensao()

        // Conta as capturas de cada peça no tabuleiro
        for (x in 0 until dimensao) {
            for (y in 0 until dimensao) {
                val piece = manager.getTabuleiro().getPecabyPosicao(x, y)

                if (piece != null && !piece.isCapturado()) {
                    val pecaNome = piece.getNome()
                    val pecaEquipa = piece.getEquipa()
                    val capturasAtuais = pecasCapturadas.getOrDefault(pecaNome, Pair(0, pecaEquipa))
                    pecasCapturadas[pecaNome] = Pair(capturasAtuais.first + piece.getCapturas(), pecaEquipa)
                }
            }
        }

        // Ordena as capturas, em ordem decrescente
        val sortedCaptures = pecasCapturadas.entries.sortedByDescending { it.value.first }

        val topPecas = mutableListOf<String>()
        var count = 0

        for ((pecaNome, capturasEquipa) in sortedCaptures) {
            val (capturas, equipa) = capturasEquipa

            if (count < 5) {
                val equipaString = if (equipa == 10) "(PRETA)" else "(BRANCA)"
                val pieceString = "$pecaNome $equipaString fez $capturas capturas"
                topPecas.add(pieceString)
                count++
            } else {
                break
            }
        }

        return topPecas
    }

    private fun top5Pontos(manager: GameManager): List<String> {
        val pecasPontos = mutableMapOf<String, Pair<Int, Int>>() // Pair<Pontuação, Equipa>
        val dimensao = manager.getTabuleiro().getDimensao()

        // Conta os pontos de cada peça no tabuleiro
        for (x in 0 until dimensao) {
            for (y in 0 until dimensao) {
                val piece = manager.getTabuleiro().getPecabyPosicao(x, y)

                if (piece != null && !piece.isCapturado()) {
                    val pecaNome = piece.getNome()
                    val pecaEquipa = piece.getEquipa()
                    val pontosAtuais = pecasPontos.getOrDefault(pecaNome, Pair(0, pecaEquipa))
                    pecasPontos[pecaNome] = Pair(pontosAtuais.first + piece.getPontuacaoCapturas(), pecaEquipa)
                }
            }
        }

        // Retira as peças com 0 pontos, ou seja, 0 capturas
        val filteredPieces = pecasPontos.filterValues { it.first > 0 }

        // Ordena os pontos, em ordem decrescente
        val sortedPoints = filteredPieces.entries.sortedWith(compareBy({ -it.value.first }, { it.key }))

        val topPecas = mutableListOf<String>()
        var count = 0

        for ((pecaNome, pontosEquipa) in sortedPoints) {
            val (pontos, equipa) = pontosEquipa

            if (count < 5) {
                val equipaString = if (equipa == 10) "(PRETA)" else "(BRANCA)"
                val pieceString = "$pecaNome $equipaString tem $pontos pontos"
                topPecas.add(pieceString)
                count++
            } else {
                break
            }
        }

        return topPecas
    }

    private fun pecasMais5Capturas(manager: GameManager): List<String> {
        val resultado = mutableListOf<String>()
        val dimensao = manager.getTabuleiro().getDimensao()

        // Percorre o tabuleiro para identificar as peças com mais de 5 capturas
        for (x in 0 until dimensao) {
            for (y in 0 until dimensao) {
                val piece = manager.getTabuleiro().getPecabyPosicao(x, y)

                if (piece != null && !piece.isCapturado()) {
                    val pecaNome = piece.getNome()
                    val pecaEquipa = piece.getEquipa()
                    val capturas = piece.getCapturas()

                    if (capturas > 5) {
                        val equipeString = if (pecaEquipa == 10) "PRETA" else "BRANCA"
                        val linha = "$equipeString: $pecaNome:$capturas"
                        resultado.add(linha)
                    }
                }
            }
        }

        return resultado
    }

    private fun pecasMaisBaralhadas(manager: GameManager): List<String> {
        val pecasInvalidas = mutableListOf<Peca>()
        val dimensao = manager.getTabuleiro().getDimensao()
        val resultado = mutableListOf<String>()

        // Percorre o tabuleiro para identificar peças com movimentos inválidos
        for (x in 0 until dimensao) {
            for (y in 0 until dimensao) {
                val peca = manager.getTabuleiro().getPecabyPosicao(x, y)

                if (peca != null && peca.getJogadasInvalidas() > 0) {
                    pecasInvalidas.add(peca)
                }
            }
        }

        // Ordena as peças pelo número de jogadas inválidas (de maior para menor)
        val sortedPecas = pecasInvalidas.sortedByDescending { peca -> peca.getJogadasInvalidas() }

        // Encontra o maior número de jogadas inválidas entre as peças
        val maiorNumeroInvalidas = sortedPecas.firstOrNull()?.getJogadasInvalidas() ?: 0

        // Filtra as peças com o maior número de jogadas inválidas
        val maisBaralhadas = sortedPecas.filter { peca -> peca.getJogadasInvalidas() == maiorNumeroInvalidas }

        // Converte para a lista de strings no formato especificado
        for (peca in maisBaralhadas) {
            val infoPeca = "${peca.getEquipa()}:${peca.getNome()}:${peca.getJogadasInvalidas()}:${peca.getJogadasValidas()}"
            resultado.add(infoPeca)
        }

        return resultado
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

        val tiposCapturados = mutableSetOf<String>()

        val dimensao = manager.getTabuleiro().getDimensao()

        // Percorre o tabuleiro para identificar os tipos das peças capturadas
        for (x in 0 until dimensao) {
            for (y in 0 until dimensao) {
                val piece = manager.getTabuleiro().getPecabyPosicao(x, y)

                if (piece != null && piece.isCapturado()) {
                    val tipoPeca = tipos[piece.getTipo()]

                    if (tipoPeca != null) {
                        tiposCapturados.add(tipoPeca)
                    }
                }
            }
        }

        return tiposCapturados.toList()
    }
}