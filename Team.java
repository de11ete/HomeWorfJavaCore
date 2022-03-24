package lesson1;

public class Team {
    private String name;
    private Participant participants[] ;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name,Participant ... participantsGiven ) {
        this.name = name;
        this.participants = participantsGiven;
    }

    public void getTeamInfo() {
        System.out.println("Команда: " + this.name );
        for (Participant participant : participants) {
            if (participant instanceof Dog) {
                System.out.println("Пёс " + participant.getName());
            }
            if (participant instanceof Cat) {
                System.out.println("Кот " + participant.getName());
            }
            if (participant instanceof Wolf) {
                System.out.println("Волк " + participant.getName());
            }
            if (participant instanceof Ferret) {
                System.out.println("Хорёк " + participant.getName());
            }
        }
    }

    public void showResults(){
        for (Participant participant : participants) {
            //obstacle.doIt(participant);
            if (!participant.isOnDistance()) {
                break;
            }
        }
    }
    public void doIt(Pool pool){
        for (Participant participant : participants) {
            pool.doIt(participant);
            /*if (!participant.isOnDistance()) {
                break;
            }*/
        }
    }
}