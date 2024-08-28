import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.AttachedFile;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import javax.management.StringValueExp;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.time.LocalDate;

public class EventListeners extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("dado")) {
            OptionMapping carasOption = event.getOption("numero");
            TextChannel targTextChannel = event.getGuild().getTextChannelById("907784613135527987");
            String jugador = event.getUser().getEffectiveName();
            if (carasOption == null) {
                event.reply("No has introducido el numero de caras del dado!").queue();
            } else {
                int caras = carasOption.getAsInt();
                int resultado = (int) Math.round(Math.random() * (caras - 1) + 1);
                targTextChannel.sendMessage(jugador + " ha tirado un d" + caras + " y ha sacado un " + resultado).queue();
                event.deferReply().queue();
                event.getHook().deleteOriginal().queue();
            }
        } else if (event.getName().equals("ficha")) {
            OptionMapping option = event.getOption("personaje");
            if (option == null) {
                event.reply("No has especificado que ficha quieres ver").queue();
                return;
            }
            String personaje = option.getAsString();
            try {
                File ficha = new File("C:\\Users\\reyes\\Desktop\\Gaspy\\Meowie Bot\\src\\main\\resources\\Fichas\\" + personaje + ".txt");
                File imagen = new File("C:\\Users\\reyes\\Desktop\\Gaspy\\Meowie Bot\\src\\main\\resources\\Fichas\\" + personaje + ".jpg");
                Scanner leer = new Scanner(ficha);
                StringBuilder texto = new StringBuilder();
                while (leer.hasNextLine()) {
                    texto.append(leer.nextLine()).append("\n");
                }
                event.reply(texto.toString()).setEphemeral(true).setFiles(FileUpload.fromData(imagen)).queue();
                leer.close();
            } catch (FileNotFoundException ex) {
                event.reply("No se ha encontrado esa ficha").queue();
            }
        } else if (event.getName().equals("rps")) {
            OptionMapping jugadaUser = event.getOption("jugada");
            if (jugadaUser == null) {
                event.reply("Tienes que especificar que vas a jugar").queue();
                return;
            }
            String jugadaJugador = jugadaUser.getAsString();
            int jugadaAlter = (int) Math.round(Math.random() * (2 - 0) + 0);
            switch (jugadaAlter) {
                case 0 -> {
                    String jugadaAlterJuego = "piedra";
                    switch (jugadaJugador) {
                        case "piedra" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nEmptamos").queue();
                        case "papel" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nTu ganas").queue();
                        case "tijeras" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nYo gano!").queue();
                    }
                }
                case 1 -> {
                    String jugadaAlterJuego = "papel";
                    switch (jugadaJugador) {
                        case "piedra" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nYo gano!").queue();
                        case "papel" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nEmpatamos").queue();
                        case "tijeras" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nTu ganas").queue();
                    }
                }
                case 2 -> {
                    String jugadaAlterJuego = "tijeras";
                    switch (jugadaJugador) {
                        case "piedra" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nTu ganas").queue();
                        case "papel" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nYo gano!").queue();
                        case "tijeras" -> event.reply("Yo he sacado " + jugadaAlterJuego + " !\nEmpatamos").queue();
                    }
                }
                default -> {
                    event.reply("Elige una jugada valida!").queue();
                }
            }
        } else if (event.getName().equals("turnos")) {
            OptionMapping turno = event.getOption("numero");
            OptionMapping accion = event.getOption("accion");
            LocalDate fecha = LocalDate.now();
            String jugador = event.getUser().getEffectiveName();
            File log = new File("C:\\Users\\reyes\\Desktop\\Gaspy\\Meowie Bot\\src\\main\\resources\\Logs\\log" + fecha + ".txt");
            if (turno != null) {
                String turnoUser = turno.getAsString();
                try {
                    FileWriter escribir = new FileWriter(log, true);
                    escribir.write(jugador + " : " + turnoUser + "\n");
                    escribir.close();
                    Scanner leer = new Scanner(log);
                    StringBuilder texto = new StringBuilder();
                    while (leer.hasNextLine()) {
                        texto.append(leer.nextLine()).append("\n");
                    }
                    leer.close();
                    event.reply(String.valueOf(texto)).queue();
                } catch (IOException ex) {
                    event.reply("Error").queue();
                }
            } else if (accion != null) {
                String accionUser = accion.getAsString();
                TextChannel targTextChannel = event.getGuild().getTextChannelById("918930011954749450");
                try {
                    FileWriter escribir = new FileWriter(log, true);
                    escribir.write(jugador + " : " + accionUser + "\n");
                    escribir.close();
                    Scanner leer = new Scanner(log);
                    StringBuilder texto = new StringBuilder();
                    while (leer.hasNextLine()) {
                        texto.append(leer.nextLine()).append("\n");
                    }
                    leer.close();
                    targTextChannel.sendMessage(jugador + " ha usado " +accionUser).queue();
                    event.reply("Accion registrada ^-^").queue();
                } catch (IOException ex) {
                    event.reply("Lo siento, Gasper la ha cagado programando").queue();
                }
            } else {
                try {
                    Scanner leer = new Scanner(log);
                    StringBuilder texto = new StringBuilder();
                    while (leer.hasNextLine()) {
                        texto.append(leer.nextLine()).append("\n");
                    }
                    leer.close();
                    event.reply(String.valueOf(texto)).queue();
                } catch (IOException ex) {
                    event.reply("Error").queue();
                }
            }
        } else if (event.getName().equals("clases")) {
            OptionMapping job = event.getOption("job");
            File dispobibles = new File("C:\\Users\\reyes\\Desktop\\Gaspy\\Meowie Bot\\src\\main\\resources\\Clases");
            if(job == null) {
                File[] archivos = dispobibles.listFiles();
                for(File archivo : archivos) {
                    event.reply(archivo.getName()).setEphemeral(true).queue();
                }
            } else if(job != null) {
                File[] archivos = dispobibles.listFiles();
                boolean encontrado = false;
                String jobValue = job.getAsString();
                for(File archivo : archivos) {
                    String nombreClaseSinExtension = archivo.getName().replaceFirst("[.][^.]+$","");
                    if(jobValue.equals(nombreClaseSinExtension)) {
                        try (Scanner leer = new Scanner(archivo)) {
                            StringBuilder texto = new StringBuilder();
                            while (leer.hasNextLine()) {
                                texto.append(leer.nextLine()).append("\n");
                            }
                            event.reply(String.valueOf(texto)).setEphemeral(true).queue();
                            leer.close();
                            encontrado = true;
                            break;
                        } catch (FileNotFoundException ex) {
                            event.reply("Error al leer el archivo: " + ex.getMessage()).setEphemeral(true).queue();
                        }
                        
                    }

                    if(!encontrado) {
                        event.reply("No se ha encontrado clase con ese nombre").setEphemeral(true).queue();
                    }
                }
            } 
        }
    }
}
