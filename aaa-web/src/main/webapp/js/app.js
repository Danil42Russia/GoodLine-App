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
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    componentWillMount() {
        jQuery.ajax({
            url: "/ajax/authority",
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
                        <th>id_user</th>
                        <th>id_role</th>
                        <th>res</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.data.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.id_user}</td>
                                <td>{item.id_role}</td>
                                <td>{item.res}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

class Activity extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    componentWillMount() {
        jQuery.ajax({
            url: "/ajax/activity",
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
                        <th>id_ur</th>
                        <th>ds</th>
                        <th>de</th>
                        <th>vol</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.data.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.id_ur}</td>
                                <td>{item.ds}</td>
                                <td>{item.de}</td>
                                <td>{item.vol}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
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
                <div id={"menu"}>
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