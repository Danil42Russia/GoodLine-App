class UserList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.userUrl);
        const data = await res.json();
        this.setState({data: data});
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

class SearchUserByID extends React.Component {
    constructor(props) {
        super(props);

        this.searchInput = React.createRef();
        this.state = {
            isClick: false
        };
    }

    render() {
        return (
            <div>
                <div id={"search"}>
                    <input ref={this.searchInput} type={"text"} onChange={() => this.setState({isClick: false})}/>
                    <button onClick={() => this.setState({isClick: true})}>Search</button>
                </div>
                {
                    this.state.isClick ?
                        <UserByID ID={this.searchInput.current.value}/> :
                        null
                }
            </div>
        );
    }
}

class UserByID extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.userUrl + "?id=" + this.props.ID);
        const data = await res.json();
        this.setState({data: data});
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
                        <tr key={this.state.data.id}>
                            <td>{this.state.data.id}</td>
                            <td>{this.state.data.login}</td>
                        </tr>
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

class UserMenu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "none"
        }
    }

    render() {
        let Child;
        switch (this.state.name) {
            case "userList": {
                Child = UserList;
                break
            }
            case "userByID": {
                Child = SearchUserByID;
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
                    <button onClick={() => this.setState({name: "userList"})}>User list</button>
                    <button onClick={() => this.setState({name: "userByID"})}>User by ID</button>
                </div>
                <div>
                    <Child/>
                </div>
            </div>
        )
    }
}