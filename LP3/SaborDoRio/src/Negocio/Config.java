package Negocio;

public class Config {

        private int cod;
        private boolean codSeguro;
        private boolean animacoes;
        private int nMesas; //número de mesas
        private int nCliPMesa; //número de clientes por mesa
       
        public int getCod() {
            return cod;
        }

        public void setCod(int cod) {
            this.cod = cod;
        }

        public boolean isCodSeguro() {
            return codSeguro;
        }

        public void setCodSeguro(boolean codSeguro) {
            this.codSeguro = codSeguro;
        }

        public boolean isAnimacoes() {
            return animacoes;
        }

        public void setAnimacoes(boolean animacoes) {
            this.animacoes = animacoes;
        }

        public int getnMesas() {
            return nMesas;
        }

        public void setnMesas(int nMesas) {
            this.nMesas = nMesas;
        }

        public int getnCliPMesa() {
            return nCliPMesa;
        }

        public void setnCliPMesa(int nCliPMesa) {
            this.nCliPMesa = nCliPMesa;
        }

    }
