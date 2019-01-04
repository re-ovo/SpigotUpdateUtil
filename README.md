## SpigotUpdateUtil
**A spigot update util**

**Dependency: Jsoup**   
```
 <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.11.3</version>
            <scope>compile</scope>
        </dependency>
```
**How to use it:**   
1. Drag the java file to your project   
2. Add the jsoup dependency to your pom.xml   
3. Edit the resource id in the java file   
4. Use it!   

**Example:**
```
 try {
            List<Update> updates = UpdateUtil.getRecentUpdates();
            for (Update update : updates) {
                System.out.println("-------------------------------\n");
                System.out.println("Title: " + update.getTitle());
                System.out.println("Content: " + update.getContent());
                System.out.println("Update Date: " + update.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
```
