package me.thejokerdev.primitivejavalang;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("plang")) {
            if (args.length == 0) {
                sender.sendMessage(ct("&c¡Por favor, ingresa un parámetro para continuar!"));
                return true;
            }
            switch (args[0]) {
                case "byte": {
                    if (getConfig().getBoolean("byte.generateRandomByte")) {
                        if (args.length == 2){
                            String arg1 = args[1];
                            Random rd = new Random();
                            byte[] arr = arg1.getBytes();
                            rd.nextBytes(arr);
                            sender.sendMessage(Arrays.toString(arr));
                        } else {
                            Random rd = new Random();
                            byte[] arr = new byte[7];
                            rd.nextBytes(arr);
                            sender.sendMessage(Arrays.toString(arr));
                        }
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "char": {
                    if (getConfig().getBoolean("char.translateChar")) {
                        if (args.length == 2){
                            String arg2 = args[1];
                            String str = new String(arg2.getBytes(StandardCharsets.US_ASCII));
                            sender.sendMessage(str);
                        } else {
                            String str = new String(command.getName().getBytes(StandardCharsets.US_ASCII));
                            sender.sendMessage(str);
                        }
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "short": {
                    if (getConfig().getBoolean("short.generateRandomShort")) {
                        if (args.length == 2){
                            String arg2 = args[1];
                            int i;
                            try {
                                i = Integer.parseInt(arg2);
                            } catch (NumberFormatException e) {
                                sender.sendMessage(ct("&c"+arg2+" no es un número."));
                                return true;
                            }
                            Random rd = new Random();
                            short s = (short) rd.nextInt(i);
                            sender.sendMessage(String.valueOf(s));
                        } else {
                            Random rd = new Random();
                            short s = (short) rd.nextInt(Short.MAX_VALUE);
                            sender.sendMessage(String.valueOf(s));
                        }
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "int": {
                    if (getConfig().getBoolean("int.generateRandomInteger")) {
                        Random rd = new Random();
                        if (args.length == 2){
                            String arg2 = args[1];
                            int i;
                            try {
                                i = Integer.parseInt(arg2);
                            } catch (NumberFormatException e) {
                                sender.sendMessage(ct("&c"+arg2+" no es un número."));
                                return true;
                            }
                            int i2 = rd.nextInt(i);
                            sender.sendMessage(String.valueOf(i2));
                        } else {
                            int i = rd.nextInt(Integer.MAX_VALUE);
                            sender.sendMessage(String.valueOf(i));
                        }
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "long": {
                    if (getConfig().getBoolean("long.generateRandomInteger")) {
                        Random rd = new Random();
                        long l = rd.nextLong();
                        sender.sendMessage(String.valueOf(l));
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "float": {
                    if (getConfig().getBoolean("float.generateRandomFloat")) {
                        Random rd = new Random();
                        float f = rd.nextFloat();
                        sender.sendMessage(String.valueOf(f));
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                case "double": {
                    if (getConfig().getBoolean("double.generateRandomDouble")) {
                        Random rd = new Random();
                        double d = rd.nextDouble();
                        sender.sendMessage(String.valueOf(d));
                    } else {
                        sender.sendMessage(ct("&c¡Esta opción está desactivada!"));
                    }
                    break;
                }
                default: {
                    String[] list = new String[]{"byte", "char", "short", "int", "long", "float", "double"};
                    StringBuilder msg = new StringBuilder();
                    for (int i = 0; i < list.length; i++) {
                        if (i == 0) {
                            msg = new StringBuilder(list[i]);
                        } else {
                            msg.append(", ").append(list[i]);
                        }
                    }
                    sender.sendMessage(ct("&7Estos son los comandos disponibles: &a" + msg));
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("plang")){
            if (args.length == 1){
                String[] list = new String[]{"byte", "char", "short", "int", "long", "float", "double"};
                List<String> list2 = new ArrayList<>();
                StringUtil.copyPartialMatches(args[0], Arrays.asList(list), list2);
                Collections.sort(list2);
                return list2;
            }
        }
        return null;
    }

    public String ct(String m){
        return ChatColor.translateAlternateColorCodes('&', m);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
