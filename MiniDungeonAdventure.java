package minidungeonadventure;

import java.util.Scanner;
import java.util.Random;

public class MiniDungeonAdventure {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random generator = new Random();

        // randomly generates player attribute values for user
        // done outside the loop because player attributes do not need to be repeated
        int yourAttack = generator.nextInt(7) + 5;
        System.out.println("Your Attack Points: " + yourAttack);
        int yourDefense = generator.nextInt(10) + 6;
        System.out.println("Your Defense Points: " + yourDefense);
        int yourDamage = generator.nextInt(5) + 2;
        System.out.println("Your Damage: " + yourDamage);
        int yourLife = generator.nextInt(20) + 25;
        System.out.println("Your Life Points: " + yourLife);
        int healthPotions = generator.nextInt(3) + 1;
        System.out.println("Number of Health Potions: " + healthPotions + "\n");

        while (true) {
            System.out.println("Hello Adventurer! Are you ready to embark? [Yes or No]");
            String embark = userInput.nextLine();

            if (embark.equals("Yes") || embark.equals("yes")) {
                System.out.println("HA! Well get a move on then, but be wary of monsters along the way!");
                break;
            } else {
                System.out.println("Well what are you bothering me for? Come back when you're ready.");
                System.out.println("");
            }
        }

        // randomly generates a name for the monster chosen out of 5 different names
        // generates 3 different monsters to fight at one time
        for (int i = 0; i < 3; i++) {
            String monsterName;
            int num = generator.nextInt(5);

            switch (num) {
                case 0:
                    monsterName = "Minor Behemoth";
                    break;

                case 1:
                    monsterName = "Deathstriker";
                    break;

                case 2:
                    monsterName = "Black Dingus";
                    break;

                case 3:
                    monsterName = "Jimmy Dean";
                    break;

                case 4:
                    monsterName = "Poison Gremlin";
                    break;

                default:
                    monsterName = "missingNo";
                    break;
            }

            System.out.println("\nA wild " + monsterName + " appears!");
            // randomly generates values for monster
            int monsterAttack = generator.nextInt(10) + 1;
            System.out.println(monsterName + " Attack Points: " + monsterAttack);
            int monsterDefense = generator.nextInt(10) + 3;
            System.out.println(monsterName + " Defense Points: " + monsterDefense);
            int monsterDamage = generator.nextInt(5) + 1;
            System.out.println(monsterName + " Damage: " + monsterDamage);
            int monsterLife = generator.nextInt(20) + 15;
            System.out.println(monsterName + " Life Points: " + monsterLife + "\n");

            boolean runaway = false;

            // boolean value randomly created to determine which party attacks
            do {
                boolean attacker = generator.nextBoolean();

                if (attacker) {
                    if (yourLife < 10 && healthPotions > 0) {
                        int healthGain = generator.nextInt(10) + 1;
                        System.out.println("You use a health potion!");
                        yourLife += healthGain;

                        if (healthGain < 6) {
                            System.out.println("Piece of shit potion! That barely did anything!");
                            System.out.println("You only regain " + healthGain + " health back.");
                        } else {
                            System.out.println("Ahh, that hit the spot! Much better!");
                            System.out.println("You regain " + healthGain + " health back.");
                        }
                        System.out.println("Your total health is: " + yourLife);
                        System.out.println("");
                        healthPotions--;
                    }

                    if (yourLife < 10 && healthPotions == 0) {
                        System.out.println("Shit, I'm out of health potions!");
                        System.out.println("Come at me bitch!");
                    }

                    System.out.println("");
                    System.out.println("You Attack!");
                    int dice = generator.nextInt(6) + 1 + generator.nextInt(6) + 1;
                    int attackValue = yourAttack + dice;
                    System.out.println("Rolled value: " + dice);
                    System.out.println("Your attack value: " + attackValue);

                    if (attackValue > monsterDefense) {
                        System.out.println("Your attack was successful!");
                        monsterLife -= yourDamage;
                        System.out.println(monsterName + "'s remaining Life Points: " + monsterLife);
                    } else {
                        System.out.println("Your attack was unsuccessful!");
                    }

                } else {
                    System.out.println(monsterName + " Attacks!");
                    int dice = generator.nextInt(6) + 1 + generator.nextInt(6) + 1;
                    int attackValue = monsterAttack + dice;
                    System.out.println("Rolled value: " + dice);
                    System.out.println(monsterName + "'s attack value: " + attackValue);

                    if (attackValue > yourDefense) {
                        System.out.println(monsterName + "'s attack was successful!");
                        yourLife -= monsterDamage;
                        System.out.println("Your remaining Life Points: " + yourLife);
                    } else {
                        System.out.println(monsterName + "'s attack was unsuccessful!");
                    }

                    System.out.println("Press ENTER to continue. (Or type in 'run' to run away)");

                    String command = userInput.nextLine();
                    switch (command) {
                        case "run":
                            System.out.println("You ran away, but not before " + monsterName + " swipes at you one last time! - 4 Life Points!");
                            yourLife -= 4;
                            runaway = true;
                            break;
                        default:
                    }
                }

            } while (yourLife > 0 && monsterLife > 0 && !runaway);

            if (yourLife <= 0) {
                System.out.println("You have died.");
                System.out.println("Darkness falls upon you.");
                break;
            }
            if (monsterLife <= 0) {
                System.out.println("You are victorious! " + monsterName + " has been slain!");
                System.out.println("You have " + yourLife + " health left.");
            }
        }
        
        if (yourLife <= 0) {
            System.out.println("GAME OVER");
        } else {
            System.out.println("Huzzah Adventurer! You have lived to fight another day!");
        }

    }

}
