package com.tinkerpop.gremlin.process.oltp.map;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.structure.Direction;
import com.tinkerpop.gremlin.structure.Edge;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.process.util.GremlinHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class EdgeVertexPipe extends FlatMapPipe<Edge, Vertex> {

    public Direction direction;

    public EdgeVertexPipe(final Traversal pipeline, final Direction direction) {
        super(pipeline);
        this.direction = direction;
        this.setFunction(holder -> {
            final List<Vertex> vertices = new ArrayList<>();
            if (this.direction.equals(Direction.IN) || this.direction.equals(Direction.BOTH)) {
                vertices.add(holder.get().getVertex(Direction.IN));
            }

            if (this.direction.equals(Direction.OUT) || this.direction.equals(Direction.BOTH)) {
                vertices.add(holder.get().getVertex(Direction.OUT));
            }
            return vertices.iterator();
        });
    }

    public String toString() {
        return GremlinHelper.makePipeString(this, this.direction);
    }
}
