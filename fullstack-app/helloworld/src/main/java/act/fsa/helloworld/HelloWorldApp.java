package act.fsa.helloworld;

import act.app.AppContext;
import act.boot.app.RunApp;
import act.event.ActEventListenerBase;
import act.job.OnAppStart;
import act.view.ActForbidden;
import org.osgl._;
import org.osgl.http.H;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Forbidden;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.N;

import javax.validation.constraints.Null;

import java.util.EventListener;
import java.util.EventObject;

import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class HelloWorldApp {

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction({"/hello", "/hi"})
    public String sayHello() {
        return "Hello Act!";
    }

    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!!");
    }

    @GetAction("/greeting")
    public void greeting(String who, int age) {
        render(who, age);
    }

    @GetAction("/product/{catalog}/{prod}/price")
    public Result price(String catalog, String prod) {
        int n = _.random(C.range(100, 400));
        String price = n + ".99";
        return render(catalog, prod, price);
    }

    @GetAction("/this/will/trigger/internal/error")
    public void internalError() {
        throw new NullPointerException();
    }

    @GetAction("/this/will/trigger/permission/denied/error")
    public Result noAccess() {
        return new ActForbidden();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }

}
