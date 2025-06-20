    package ehu.java.composite.impl;

    import ehu.java.composite.TextComponent;
    import java.util.ArrayList;
    import java.util.List;

    public class TextComposite implements TextComponent {
        private final ComponentType type;
        private final List<TextComponent> components = new ArrayList<>();

        public TextComposite(ComponentType type) {
            this.type = type;
        }

        @Override
        public void add(TextComponent component) {
            components.add(component);
        }

        @Override
        public void remove(TextComponent component) {
            components.remove(component);
        }

        @Override
        public List<TextComponent> getChild() {
            return List.copyOf(components);
        }

        @Override
        public ComponentType getType() {
            return type;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (type == ComponentType.TEXT && !components.isEmpty()) {
                builder.append("\t");
            }

            for (int i = 0; i < components.size(); i++) {
                TextComponent component = components.get(i);
                builder.append(component.toString());

                if (i < components.size() - 1) {
                    switch (type) {
                        case TEXT -> builder.append("\n\t");
                        case PARAGRAPH, SENTENCE -> builder.append(" ");
                        case LEXEME -> {
                        }
                        default -> {
                        }
                    }
                }
            }

            return builder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TextComposite that = (TextComposite) o;
            return type == that.type &&
                    components.equals(that.components);
        }
    }