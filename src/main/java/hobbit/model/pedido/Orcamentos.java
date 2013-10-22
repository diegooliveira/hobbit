package hobbit.model.pedido;

/**
 * Representação do repositório de orcamentos.
 */
public interface Orcamentos {

    /**
     * Salva un novo orçamento.
     */
    void save(Orcamento orcamento);

    /**
     * Pega o póximo ID disponível para o orçamento.
     * 
     * @return O novo Id maior que zero.
     */
    int nextId();

    /**
     * Encontra o orçamento pelo ID.
     * 
     * @param id
     *            O id do orçamento
     * @return Uma instancia carregada do Orçamento.
     */
    Orcamento porId(int id);

}
