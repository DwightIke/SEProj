package main.model;

/**
 * Created by Dragos on 5/8/2017.
 */
public class Conference
{
    int idConference;
    String name;
    int noParticipants;


    public Conference(int idConference,String name, int noParticipants) {
        this.idConference = idConference;
        this.name = name;
        this.noParticipants = noParticipants;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "idConference=" + idConference +
                ", name='" + name + '\'' +
                ", noParticipants=" + noParticipants +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public int getNoParticipants() {
        return noParticipants;
    }

    public void setNoParticipants(int noParticipants) {
        this.noParticipants = noParticipants;
    }
}
