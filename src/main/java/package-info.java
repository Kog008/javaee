/*
        A typical web application found in any Java EE space implements the so called model-view-controller pattern.
        Known as MVC Pattern, or MVC paradigm. It is meaning of three different application layers.

        (1) [M]odel: The bottommost layer of an application is the model, defined by the technical and procedural requirements,
            which my software has to represent. It is the part of the real world, which the software has to figure out
            to work with. These classes, known as POJOs - plain old java objects - are found in the model package.

            In case of Hibernate, this model determines our database model. But there are basic approaches in which
            the relational database model itself represents in the same time the application model.

        (2) [C]ontroller: Usually the model layer is targeted by several controller layers.
            It depends on the use case whether these layers are serial, or parallel to each other.
            The controller contain the model manipulating functionality regarding to the use cases.

        (3) [V]iew: Finally the topmost layer is the view layer. Views are displaying the model to the user and
            provide functionality to trigger the controller in dependency to the several use cases.
            Views are the interface to the user to work with the application model.

        Data basically is delivery outgoing from the view → controller (service → repository) → database.
        Or in reverse direction.
 */