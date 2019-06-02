class AuthorityList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.authorityUrl);
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

/* Search Authority By ID */

class SearchAuthorityByID extends React.Component {
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
                        <AuthorityByID id={this.searchInput.current.value}/> :
                        null
                }
            </div>
        );
    }
}

class AuthorityByID extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.authorityUrl + "?id=" + this.props.id);
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

/* Search Authority By UserID */

class SearchAuthorityByUserID extends React.Component {
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
                        <AuthorityByUserID id={this.searchInput.current.value}/> :
                        null
                }
            </div>
        );
    }
}

class AuthorityByUserID extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.authorityUrl + "?userId=" + this.props.id);
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

class AuthorityMenu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "none"
        }
    }

    render() {
        let Child;
        switch (this.state.name) {
            case "authorityList": {
                Child = AuthorityList;
                break
            }
            case "authorityByID": {
                Child = SearchAuthorityByID;
                break
            }
            case "authorityByUserId": {
                Child = SearchAuthorityByUserID;
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
                    <button onClick={() => this.setState({name: "authorityList"})}>Authority list</button>
                    <button onClick={() => this.setState({name: "authorityByID"})}>Authority by ID</button>
                    <button onClick={() => this.setState({name: "authorityByUserId"})}>Authority by User ID</button>
                </div>
                <div>
                    <Child/>
                </div>
            </div>
        )
    }
}