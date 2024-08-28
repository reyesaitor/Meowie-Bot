import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String [] args) throws LoginException, InterruptedException {

        JDA Meowie = JDABuilder.createDefault("MTIyMTQyNzI5Mzg4OTQ5NTA5MA.GJbLu7.8r0jLFcQc_ezuRFoo1_bVOWJECMOwLMW56lFKU")
                .setActivity(Activity.playing("Derretirse por el puto verano"))
                .addEventListeners(new EventListeners())
                .build().awaitReady();

        Guild guild = Meowie.getGuildById("907779110422581330");

        if(guild != null) {
                guild.upsertCommand("dado", "Alter tira un dado y te dice el resultado")
                        .addOption(OptionType.INTEGER, "numero", "Introduce el numero del dado")
                        .queue();
                guild.upsertCommand("ficha", "Da informacion sobre la ficha seleccionada")
                        .addOption(OptionType.STRING, "personaje", "personaje del que quieres ver la ficha", true)
                        .queue();
                guild.upsertCommand("rps", "Juega a piedra, papel o tijeras con Alter")
                        .addOption(OptionType.STRING, "jugada", "tu eleccion en el juego", true)
                        .queue();
                guild.upsertCommand("turnos", "Da informacion de los turnos de combate")
                        .addOption(OptionType.INTEGER,"numero", "Introduce tu turno")
                        .addOption(OptionType.STRING,"accion","Introduce tu accion")
                        .queue();
                guild.upsertCommand("clases","Info de los jobs disponibles en el rol")
                        .addOption(OptionType.STRING,"job","Info de jobs, dejar en blanco para ver disponibles")
                        .queue();
        
        
        }


    }


}