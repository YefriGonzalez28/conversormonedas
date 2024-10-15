public class Conversor {
    private String codigobase;
    private String codigoconv;
    private double equivalencia;
    private double resultado;

    public Conversor(ConversorExch conversorExch) {
        this.codigobase = conversorExch.base_code();
        this.codigoconv = conversorExch.target_code();
        this.equivalencia = Double.parseDouble(conversorExch.conversion_rate());
        this.resultado = Double.parseDouble(conversorExch.conversion_result());
    }

    public String getCodigobase() {
        return codigobase;
    }

    public void setCodigobase(String codigobase) {
        this.codigobase = codigobase;
    }

    public String getCodigoconv() {
        return codigoconv;
    }

    public void setCodigoconv(String codigoconv) {
        this.codigoconv = codigoconv;
    }

    public double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(String equivalencia) {
        this.equivalencia = Double.parseDouble(equivalencia);
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = Double.parseDouble(resultado);
    }

    @Override
    public String toString() {
        return "( De='" + codigobase + '\'' +
                ", A='" + codigoconv + '\'' +
                ", equivalencia='" + "1" + codigobase + "=" + String.format("%.3f",equivalencia/1000) + codigoconv+ '\'' +
                ", resultado='" + String.format("%.3f",resultado/1000) +  codigoconv + ")";
    }
}
