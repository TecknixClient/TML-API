# Tecknix Mod Loader API
**THIS API IS NOT COMPLETE AND WILL NOT YET ALLOW YOU TO MOD THE CLIENT**

[![Discord](https://img.shields.io/badge/chat%20on-discord-7289DA)](https://discord.gg/qn7BsjHHbN)

**ATTENTION MODDERS!**
This repository contains the modding API not the MDK (Mod Development Kit). This repository will not give you the ability to mod Tecknix Client but you can contribute to the repository if you have events you would like to add. 

# What is this?
This repository holds the source code for TML (Tecknix Mod Loader)'s API. Inside this repository you will find the event system, menu addition, Bytecode transformers and the Mixin event hooks. 

# License

This repository is licenced under the GNU General Public License. Any additions or contributions must include the license header. 

## Setting up

- Open the gradle project in Intellij IDEA or Eclipse.
- Run "setupDecompWorkspace" then "setupCiWorkspace".
![Image](https://imgur.com/pbLxpi5.png)
<br/>
- Run "genIntellijRuns".
![Image](https://imgur.com/gsBTgGv.png)
<br/>
- Open the run configuration's settings and change the classpath.
![Image](https://imgur.com/DACiMTi.png)
<br/>
**And you are done!**
## Contributing

Added an event? Amazing! Be sure to create a pull request with your changes on this repository. Remember to keep our [Code Style](#code-style) in mind!

## Code Style

### Classes:
Classes should have a header containing a short description of the class' purpose.
```java
    /**
     * TMTickEvent
     * 
     * This event is called in the {@link net.minecraft.client.Minecraft} class.
     * It can be used for calling things constantly on every tick of the game.
     * 
     * @author Tecknix Software
     */
    public class TMTickEvent extends TMEvent {
    
    }
```

### Methods:
Methods should have a header including the purpose of the method, the author of the method and the method's parameters.

```java
/**  
 * Post an event to the API. * * @param event Passes through the event to post.  
 * @author Tecknix Software.  
 */
 public static void post(final TMEvent event) {  
	  //Gets the content object from the map if the key matches that of this events class.  
	  final ArrayList<EventContent> contents = EVENT_REGISTRY.get(event.getClass());  
	  if (contents != null) {  
		  for (final EventContent content : contents) {  
				  event.invoke(content);  
		  } 
	  } 
 }


