import java.util.*;

abstract class Content {
    private final String title;

    Content(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void render();
}

class TextContent extends Content {
    private final String text;

    TextContent(String title, String text) {
        super(title);
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println("Text: " + text);
    }
}

class ImageContent extends Content {
    private final String resolution;

    ImageContent(String title, String resolution) {
        super(title);
        this.resolution = resolution;
    }

    @Override
    public void render() {
        System.out.println("Rendering image: " + getTitle()
                + " [" + resolution + "]");
    }
}

class VideoContent extends Content {
    private final int duration;

    VideoContent(String title, int duration) {
        super(title);
        this.duration = duration;
    }

    @Override
    public void render() {
        System.out.println("Playing video: " + getTitle()
                + " (" + duration + " seconds)");
    }
}

class ContentGroup extends Content {
    private final List<Content> contents = new ArrayList<>();

    ContentGroup(String title) {
        super(title);
    }

    public void addContent(Content content) {
        contents.add(content);
    }

    @Override
    public void render() {
        System.out.println("Rendering group: " + getTitle());

        for (Content content : contents)
            content.render();
    }
}

class RenderingEngine {
    public void process(Content content) {
        content.render();
    }
}

public class ContentRenderingEngine {
    public static void main(String[] args) {
        Content text = new TextContent(
                "Introduction", "Welcome to Digital Studio");

        Content image = new ImageContent(
                "Landscape", "1920x1080");

        Content video = new VideoContent(
                "Introduction Video", 120);

        ContentGroup presentation = new ContentGroup("Presentation");

        presentation.addContent(text);
        presentation.addContent(image);
        presentation.addContent(video);

        RenderingEngine engine = new RenderingEngine();
        engine.process(presentation);
    }
}
