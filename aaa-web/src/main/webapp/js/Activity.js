class ActivityList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.activityUrl);
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

/* Search Activity By ID */

class SearchActivityByID extends React.Component {
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
                        <ActivityByID id={this.searchInput.current.value}/> :
                        null
                }
            </div>
        );
    }
}

class ActivityByID extends React.Component {
    constructor(props) {
        super(props);

        console.log("1");

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.activityUrl + "?id=" + this.props.id);
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

/* Search Activity By Authority ID */

class SearchActivityByAuthorityID extends React.Component {
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
                        <ActivityByAuthorityID id={this.searchInput.current.value}/> :
                        null
                }
            </div>
        );
    }
}

class ActivityByAuthorityID extends React.Component {
    constructor(props) {
        super(props);

        console.log("1");

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch(config.activityUrl + "?authorityId=" + this.props.id);
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

class ActivityMenu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "none"
        }
    }

    render() {
        let Child;
        switch (this.state.name) {
            case "activityList": {
                Child = ActivityList;
                break
            }
            case "activityByID": {
                Child = SearchActivityByID;
                break
            }
            case "activityByAuthorityID": {
                Child = SearchActivityByAuthorityID;
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
                    <button onClick={() => this.setState({name: "activityList"})}>Activity list</button>
                    <button onClick={() => this.setState({name: "activityByID"})}>Activity by ID</button>
                    <button onClick={() => this.setState({name: "activityByAuthorityID"})}>Activity by Authority ID
                    </button>
                </div>
                <div>
                    <Child/>
                </div>
            </div>
        )
    }
}