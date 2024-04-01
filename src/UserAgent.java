public class UserAgent {
final String system;
final String browser;

    public String getSystem() {
        return system;
    }
    public String getBrowser() {
        return browser;
    }
    public UserAgent(String userAgentString) {
            if (!userAgentString.equals("-")&&userAgentString.contains("(")) {
                String[] splitLine = userAgentString.split("/");
                this.browser = splitLine[0];

                splitLine =userAgentString.split("\\(");
                String firstBrackets = splitLine[1];
                String[] parts = firstBrackets.split(";");
                String fragment = parts[0];
                if (parts.length >= 2) {
                    fragment = parts[0];
                }
                this.system = fragment;
            }
            else
            {
                this.browser="-";
                this.system ="-";
            }
     }
}

