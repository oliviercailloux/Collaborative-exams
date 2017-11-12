package Entity;

public class Author  {

	private int idAuthor;
	private String nameAuthor;
	private String email;
	
	
	public Author(int idAuthor,String nameAuthor, String email)
	{
		this.idAuthor = idAuthor; 
		this.email = email; 
		this.nameAuthor = nameAuthor; 
	}

	public Author() {
		
	}

	public int getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameauthor) {
		nameAuthor = nameauthor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
	return "[Author id: " + this.getIdAuthor() + ", nom:" + this.getNameAuthor() + ", Email: "+this.getEmail() + "]";
	}

}
