package demo.helloworld;

import act.Act;
import act.inject.DefaultValue;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class HelloWorldApp {

    @GetAction
    public void home(@DefaultValue("World") @Output String who) {
    }

    @PostAction("/json")
    public String json() {
        return "你好啊";
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
