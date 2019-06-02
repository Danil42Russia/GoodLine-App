class User extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch('/ajax/user');
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