package model;

import gen.proyectoParser;

public class Task {
    private final String name;
    private final proyectoParser.BloqueContext body;

    public Task(String name, proyectoParser.BloqueContext body) {
        this.name = name;
        this.body = body;
    }

    public String getName() { return name; }
    public proyectoParser.BloqueContext getBody() { return body; }
}
