package gestorAplicacion.Clientes;

public  class Animal {
	final static double valorsobrecargo=2000;
	public Cliente duenno;
	public Cliente getDuenno() {
		return duenno;
	}

	public void setDuenno(Cliente duenno) {
		this.duenno = duenno;
	}

	public int edad;
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String nombreMascota;

	
	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}


	
	public Animal(Cliente duenno, int edad, String nombre){
		this.duenno=duenno;
		this.edad=edad;
		this.nombreMascota= nombre;

	}
	public double Sobrecargo(){
		double valor= this.edad*Animal.valorsobrecargo;
		return valor;
	}

}
