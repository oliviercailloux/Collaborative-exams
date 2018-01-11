package model.entity.question;
import model.entity.Person;

public class SameAbility {
	
	private int idSameAbility;
	private Question id_question1;
	private Question id_question2;
	private Person id_author;
	
	
	public SameAbility(int id,Question q1,Question q2,Person p1)
	{
		idSameAbility =id; 
		id_question1=q1;
		id_question2=q2;
		id_author=p1;
	}
	
	
	public boolean isSameAbility(Question q1,Question q2)
	{
		if(id_question1.equals(q1) && id_question2.equals(q2))
		{
			return true;
		}
		else if(id_question1.equals(q2) && id_question2.equals(q1)) {
			return true;
		}
		
		return false;
	}
	
	
	
	
}
