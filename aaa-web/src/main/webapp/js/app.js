class None extends React.Component {
    render() {
        return (
            <div>
            </div>
        )
    }
}

class User extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    componentWillMount() {
        jQuery.ajax({
            url: "/ajax/user",
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.url, status, err.toString());
            }.bind(this)
        });
    }

    render() {
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>login</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.data.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.login}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

class Authority extends React.Component {
    render() {
        return (
            <div>
                Authority
            </div>
        )
    }
}

class Activity extends React.Component {
    render() {
        return (
            <div>
                Activity
            </div>
        )
    }
}

class Menu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "none"
        }
    }

    render() {
        let Child;
        switch (this.state.name) {
            case "user": {
                Child = User;
                break
            }
            case "authority": {
                Child = Authority;
                break
            }
            case "activity": {
                Child = Activity;
                break
            }
            default: {
                Child = None;
                break
            }
        }

        return (
            <div>
                <div>
                    <button onClick={() => this.setState({name: "user"})}>User list</button>
                    <button onClick={() => this.setState({name: "authority"})}>Authority list</button>
                    <button onClick={() => this.setState({name: "activity"})}>Activity list</button>
                </div>
                <div>
                    <Child/>
                </div>
            </div>
        )
    }
}

ReactDOM.render(
    <Menu/>,
    document.getElementById("root")
);